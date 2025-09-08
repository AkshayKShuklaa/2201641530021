const BASE_URL = "http://localhost:8080/shorturls";

export async function createShortUrl(urlData) {
  const response = await fetch(BASE_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(urlData),
  });
  return response.json();
}

export async function getShortUrlStats(shortcode) {
  const response = await fetch(`${BASE_URL}/${shortcode}`);
  return response.json();
}
