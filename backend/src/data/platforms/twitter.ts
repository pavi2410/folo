import type { ProfileResult } from ".";

export async function fetchTwitterProfile(
  username: string
): Promise<ProfileResult | null> {
  const options = {
    method: "POST",
    headers: {
      cookie:
        "guest_id_marketing=v1%253A168709844043604425; guest_id_ads=v1%253A168709844043604425; personalization_id=%22v1_PFdcK%2F%2BUTsMB6QL41wa9uA%3D%3D%22; guest_id=v1%253A168709844043604425",
      Authorization:
        "Bearer AAAAAAAAAAAAAAAAAAAAAPYXBAAAAAAACLXUNDekMxqa8h%2F40K4moUkGsoc%3DTYfbDKbT3jJPCEVnMYqilB28NHfOPqkca3qaAxGfsyKCs0wRbw",
    },
  };

  const url = new URL(
    "https://api.twitter.com/graphql/4S2ihIKfF3xhp-ENxvUAfQ/UserByScreenName"
  );

  url.searchParams.set(
    "variables",
    JSON.stringify({
      screen_name: username,
      withHighlightedLabel: true,
    })
  );

  const res = await fetch(url, options);
  const json = await res.json();

  return {
    platform: "twitter",
    username,
    followers: json.data.user.legacy.followers_count,
    updatedAt: Date.now(),
  };
}
