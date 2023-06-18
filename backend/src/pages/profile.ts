import type { APIContext } from "astro";

import { fetchTwitterProfile } from "../data/platforms/twitter.js";
import { fetchYoutubeProfile } from "../data/platforms/youtube.js";

export async function get({ url }: APIContext) {
  const platform = url.searchParams.get("platform");
  const username = url.searchParams.get("username");

  if (platform === null || username === null) {
    return new Response("Missing platform or username", {
      status: 400,
    });
  }

  let profile;

  if (platform === "Twitter") {
    profile = await fetchTwitterProfile(username);
  } else if (platform === "Youtube") {
    profile = await fetchYoutubeProfile(username);
  } else {
    return new Response("Invalid platform", {
      status: 400,
    });
  }

  return new Response(JSON.stringify(profile), {
    status: 200,
    headers: {
      "Content-Type": "application/json",
    },
  });
}
