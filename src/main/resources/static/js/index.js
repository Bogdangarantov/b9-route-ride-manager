let app = new Vue({
    mode: 'production',
    el: '#app',
    data: {
        user:null,
        cookieValue:"",
        service : {},
        from:"",
        to:"",
        departure_date:"",
        baggage:0,
        allCities:[]
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
        goSearch(){
            if(this.from.length!==0 && this.to.length!==0 && this.departure_date.length!==0){
                window.location.href = "/search?from="+this.from+'&to='+this.to+'&departure_date='+this.departure_date+'&baggage='+this.baggage
            }else{
                alert("You didn`t filled all the queries")
            }
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