import { Hono } from 'hono'
import { single } from "./main.js";

const app = new Hono()

// Use PORT provided in environment or default to 3000
const PORT = process.env.PORT ?? 3000;

app.get('/', (c) => {
    return c.text('Hello World from folo!')
})

app.get('/notify', async (c) => {
    const { profile_id } = c.req.query()
    console.log('Notified about', profile_id)
    await single(profile_id)
    return c.body(null)
})

// Listen on `port` and 0.0.0.0
// app.listen(port, "0.0.0.0", () => {
//     console.log(`Example app listening on port ${port}`)
// })

export default {
    port: PORT,
    fetch: app.fetch,
}
