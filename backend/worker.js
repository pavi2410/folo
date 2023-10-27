const BASE_URL = "https://folo-workers.pavi2410.workers.dev"

export async function getProfile(profile) {
    let u = new URL('/profile', BASE_URL)
    u.searchParams.set('platform', profile.platform)
    u.searchParams.set('username', profile.username)
    u.searchParams.set('metric', profile.metric)

    let res = await fetch(u)

    res = await res.json()

    return res?.metrics ?? {}
}
