let app = new Vue({
    mode: 'production',
    el: '#app',
    data: {
        user: null,
        cookieValue: "",
        service: {},
        from: {},
        to: {},
        departure_date: "",
        passengers: 0,
        baggage: 0,
        allCities: [],
        date: "",
        routes: []
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

            this.from = this.allCities.filter(city => city.id === parseInt(link.get("from")))[0]
            this.to = this.allCities.filter(city => city.id === parseInt(link.get("to")))[0]
            this.departure_date = link.get("departure_date")
            this.date = this.formatDate(this.departure_date)
            this.baggage = link.get("baggage")
            await this.search()
        },
        async search(event) {
            if (event) event.preventDefault()
            let response = await fetch("/api/v1/search?from=" + this.from.id + "&to=" + this.to.id + "&departure_date=" + this.departure_date + "&baggage=" + this.baggage, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "X-XSRF-TOKEN": this.cookieValue
                },
            })
            if (response.ok) {
                let data = JSON.parse(await response.text())
                console.log(data)
                this.routes = data
                // this.allCities = JSON.parse(await response.text())
                // console.log(this.allCities)
            }
        },
        async getCities() {
            let response = await fetch("/api/v1/city", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "X-XSRF-TOKEN": this.cookieValue
                },
            })
            if (response.ok) {
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
    async created() {
        this.cookieValue = this.getCookie('XSRF-TOKEN')
        this.getUser()
        await this.getCities()
        await this.getParameters()
    }
})