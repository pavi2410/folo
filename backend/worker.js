const BASE_URL = "https://folo-workers.pavi2410.workers.dev"

export async function getProfile(profile) {
    const u = new URL('/profile', BASE_URL)
    u.searchParams.set('platform', profile.platform)
    u.searchParams.set('username', profile.username)
    u.searchParams.set('metric', profile.metric)

    const res = await fetch(u)

    if (!res.ok) {
        console.warn(`Failed to fetch profile: ${res.statusText}`)
        return {}
    }

    const data = await res.json()

    return data?.metrics ?? {}
}
