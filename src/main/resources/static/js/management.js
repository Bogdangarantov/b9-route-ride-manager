let app = new Vue({
    mode: 'production',
    el: '#app',
    data: {
        user:null,
        cookieValue:"",
        loading: false,
        newUser:{
            name:"",
            login:"",
            password:"",
            roles:[]
        },
        userTicketPrice:"",
        baggageTicketPrice:"",
        depCity: {},
        depTime:"",
        transitCity:[
            {
                id:""
            }
        ],
        arrivalCity: {},
        arrivalTime:"",
        allCities:[],
        newCity:{
            name:""
        }
    },
    methods:{
        getCookie(name) {
            const value = `; ${document.cookie}`;
            const parts = value.split(`; ${name}=`);
            if (parts.length === 2) return parts.pop().split(';').shift();
        },
        getUser(){
            let th = this
            let user = JSON.parse(document.getElementById("user").value)
            th.user = user
            user.value =""
        },
        async addUser(event){
            event.preventDefault()
            let response = await fetch("/api/v1/users",{
                method:"POST",
                headers:{
                    "Content-Type":"application/json",
                    "X-XSRF-TOKEN": this.cookieValue
                },
                body:JSON.stringify(this.newUser)
            })
            if (response.ok){
                alert("User with name "+this.newUser.name+" created")
                location.reload()
            }
        },
        addTransitCity(){
            this.transitCity.push({id:""})
        },
        async createRoute(event){
            event.preventDefault()
            console.log(this.depCity)
            console.log(this.depTime)
            console.log(this.transitCity)
            console.log(this.arrivalCity)
            console.log(this.arrivalTime)
            console.log(this.userTicketPrice)
            console.log(this.baggageTicketPrice)
            let data = {
                departureCity:this.depCity,
                departureTime: this.depTime,
                transitCities:this.transitCity,
                arrivalCity: this.arrivalCity,
                arrivalTime: this.arrivalTime,
                userTicketPrice: this.userTicketPrice,
                baggageTicketPrice:this.baggageTicketPrice

            }
            let response = await fetch("/api/v1/route",{
                method:"POST",
                headers:{
                    "Content-Type":"application/json",
                    "X-XSRF-TOKEN": this.cookieValue
                },
                body:JSON.stringify(data)
            })
            if (response.ok){
                alert("User with name "+this.newCity.name+" created")
                location.reload()
            }
        },
        async addCity(event){
            event.preventDefault()
            let response = await fetch("/api/v1/city",{
                method:"POST",
                headers:{
                    "Content-Type":"application/json",
                    "X-XSRF-TOKEN": this.cookieValue
                },
                body:JSON.stringify(this.newCity)
            })
            if (response.ok){
                alert("User with name "+this.newCity.name+" created")
                location.reload()
            }
        },
        async startLoading() {
            this.loading = true
        },
        async stopLoading() {
            this.loading = false
        },
        async getCities(){
            let response = await fetch("/api/v1/city",{
                method:"GET",
                headers:{
                    "Content-Type":"application/json",
                    "X-XSRF-TOKEN": this.cookieValue
                },
            })
            if (response.ok){
                this.allCities = JSON.parse(await response.text())
                console.log(this.allCities)
            }
        },
        async logout() {
            let response = await fetch('/logout', {
                method: 'POST',
                headers: {
                    'X-XSRF-TOKEN': this.cookieValue
                }
            })
            if (response.ok) {
                await location.reload()
            }
        },
    },
    async created(){
        this.cookieValue = this.getCookie('XSRF-TOKEN')
        this.getUser()
        await this.getCities()
    }
})