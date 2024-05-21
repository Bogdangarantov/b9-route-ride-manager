let app = new Vue({
    mode: 'production',
    el: '#app',
    data: {
        user: null,
        cookieValue: "",
        service: {},
        date: "",
        currentRoute:{},
        price:"",
        newTicket:{
            firstName:"",
            lastName:"",
            baggage:0,
            email:"",
            phone:""
        }
    },
    methods: {
        getCookie(name) {
            const value = `; ${document.cookie}`;
            const parts = value.split(`; ${name}=`);
            if (parts.length === 2) return parts.pop().split(';').shift();
        },
        getUser() {
            let th = this
            let user = JSON.parse(document.getElementById("user").value)
            th.user = user
            user.value = ""
        },
        formatDate(inputDate) {
            // Parse the input date string to a Date object
            const date = new Date(inputDate);

            // Create an Intl.DateTimeFormat object with the desired output pattern
            const formatter = new Intl.DateTimeFormat('en-US', {
                weekday: 'short',
                day: 'numeric',
                month: 'short'
            });

            // Format the date
            return formatter.format(date);
        },
        formatTime(dateTimeString) {
            // Parse the input date-time string to a Date object
            const date = new Date(dateTimeString);

            // Extract the time part with hour and minute only
            const time = date.toLocaleTimeString('en-US', {
                hour: '2-digit',
                minute: '2-digit',
                hour12: false // Use 24-hour format
            });

            return time;
        },
        getTimeDifference(startTime, endTime) {
            // Parse the input date-time strings to Date objects
            const start = new Date(startTime);
            const end = new Date(endTime);

            // Calculate the difference in milliseconds
            const diffInMs = end - start;

            // Convert the difference from milliseconds to hours and minutes
            const diffInMinutes = Math.floor(diffInMs / 60000);
            const hours = Math.floor(diffInMinutes / 60);
            const minutes = diffInMinutes % 60;

            // Format the output string
            const formattedTimeDifference = `${hours}:${minutes < 10 ? '0' + minutes : minutes} hrs`;

            return formattedTimeDifference;
        },
        async getParameters() {
            let link = new URLSearchParams(window.location.search)

            this.route_id = link.get("route_id")
            this.price = link.get("price")
            await this.getRoute()
        },
        async getRoute(){
            let response = await fetch('/api/v1/route/'+this.route_id, {
                method: 'GET',
                headers: {
                    'X-XSRF-TOKEN': this.cookieValue
                }
            })
            if (response.ok) {
                let data = JSON.parse(await response.text())
                console.log(data)
                this.currentRoute = data
            }
        },
        async buyTicket(event){
            event.preventDefault()
            if (
                this.newTicket.phone.length!==0 &&
                this.newTicket.firstName.length!==0 &&
                this.newTicket.lastName.length!==0 &&
                this.newTicket.email.length!==0
            ){
                let data = {
                    routeId:this.currentRoute,
                    departureCity: this.currentRoute.departureCity,
                    arrivalCity: this.currentRoute.arrivalCity,
                    ticketStatus: "Good",
                    seatNumber:"5",
                    dateOfSelling: new Date(),
                    passangerId:this.user.id,
                    price:parseFloat(this.price)
                }
                console.log(data)
                let response = await fetch('/api/v1/ticket', {
                    method: 'POST',
                    headers: {
                        "Content-type":"application/json",
                        'X-XSRF-TOKEN': this.cookieValue
                    },
                    body:JSON.stringify(data)
                })
                if (response.ok) {
                    alert("Ticket bought succesfully")
                    await location.reload()
                }
            }else{
                alert("Fill all the fields please")
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
    async created() {
        this.cookieValue = this.getCookie('XSRF-TOKEN')
        this.getUser()
        await this.getParameters()
    }
})