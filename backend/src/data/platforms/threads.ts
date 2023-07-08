import type { ProfileMetric } from '.';

const REGEX = {
	followers: /<meta name="description" content=".*? (\d+) Followers." \/>/,
};

export async function fetchThreadsProfile(username: string): Promise<ProfileMetric[] | null> {
	const url = new URL('https://www.threads.net/@' + username);

	const res = await fetch(url, {
		headers: {
			'User-Agent': 'Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)',
		}
	});
	const html = await res.text();

	console.log(html);

	const matches = html.match(REGEX.followers);
	if (!matches) {
		return null;
	}
	const followers = parseInt(matches[1]) ?? -1;

	return [
		{
			metric: 'followers',
			value: followers,
		},
	];
}
