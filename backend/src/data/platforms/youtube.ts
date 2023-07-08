import type { ProfileMetric } from '.';

export async function fetchYoutubeProfile(username: string): Promise<ProfileMetric[] | null> {
	const res = await fetch('https://youtube.com/@' + username);
	const json = await res.text();

	const subscribers_count = [...json.matchAll(/"simpleText":"(.*?) subscribers"/g)].map((m) => m[1]).at(-1) ?? '0';

	return [
		{
			metric: 'subscribers',
			value: compactStringToNumber(subscribers_count),
		},
	];
}

function compactStringToNumber(compactString: string): number {
	const regex = /^(\d*\.?\d+)([KMBT])?$/i;
	const match = compactString.match(regex);

	if (match) {
		const number = parseFloat(match[1]);
		const unit = match[2];

		const multipliers = {
			K: 1e3,
			M: 1e6,
			B: 1e9,
			T: 1e12,
		};

		const multiplier = unit ? multipliers[unit] : 1;

		return number * multiplier;
	}

	return NaN; // If the compactString does not match the expected format
}
