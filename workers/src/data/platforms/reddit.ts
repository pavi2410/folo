import type { ProfileResult } from '.';

export async function fetchProfile(username: string): Promise<ProfileResult['metrics'] | null> {
	const res = await fetch(`https://www.reddit.com/user/${username}/about.json`, {
		headers: {
			'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/119.0',
		},
	});
	const json = await res.json();

	const karma = json.data.total_karma;

	return {
		karma,
	};
}
