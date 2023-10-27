import express from 'express'
import {main, single} from "./main.js";
const app = express()
const port = 3000

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

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})
