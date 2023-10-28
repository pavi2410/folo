import express from 'express'
import cron from 'node-cron'
import {main, single} from "./main.js";
const app = express()

// Use PORT provided in environment or default to 3000
const port = process.env.PORT || 3000;

cron.schedule('0 0 * * *', async () => {
    console.time('cron job')
    await main()
    console.timeEnd('cron job')
})

app.get('/', (req, res) => {
    res.send('Hello World!')
})

app.get('/cron', async (req, res) => {
    await main()
    res.end()
})

app.get('/notify', async (req, res) => {
    const {profile_id} = req.query
    console.log('Notified about', profile_id)
    await single(profile_id)
    res.end()
})

// Listen on `port` and 0.0.0.0
app.listen(port, "0.0.0.0", () => {
    console.log(`Example app listening on port ${port}`)
})
