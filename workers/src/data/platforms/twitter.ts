import type { ProfileResult } from '.';

const REGEX = {
	followers: /<li class="followers">.*?<\/span>.*?<span.*?>(.*?)<\/span>/s,
};

export async function fetchProfile(username: string): Promise<ProfileResult['metrics'] | null> {
	const res = await fetch('https://n.biendeo.com/' + username, {
		headers: {
			'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/119.0',
		},
	});
	const html = await res.text();

	const matches = html.match(REGEX.followers);
	if (!matches) {
		return null;
	}

	const followers = parseInt(matches[1].replaceAll(',', '')) ?? -1;

	return {
		followers,
		subscribers: 0,
	};
}
