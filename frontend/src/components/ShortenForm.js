import React, { useState } from "react";

export default function ShortenForm({ onShorten }) {
  const [url, setUrl] = useState("");
  const [validity, setValidity] = useState("");
  const [shortcode, setShortcode] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!url) return alert("URL is required");
    const data = { url };
    if (validity) data.validity = parseInt(validity);
    if (shortcode) data.shortcode = shortcode;
    onShorten(data);
    setUrl("");
    setValidity("");
    setShortcode("");
  };

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: "20px" }}>
      <input
        type="text"
        placeholder="Enter URL"
        value={url}
        onChange={(e) => setUrl(e.target.value)}
        required
      />
      <input
        type="number"
        placeholder="Validity (minutes)"
        value={validity}
        onChange={(e) => setValidity(e.target.value)}
      />
      <input
        type="text"
        placeholder="Custom Shortcode"
        value={shortcode}
        onChange={(e) => setShortcode(e.target.value)}
      />
      <button type="submit">Shorten</button>
    </form>
  );
}
