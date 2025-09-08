import React, { useState } from "react";
import ShortenForm from "./components/ShortenForm";
import UrlList from "./components/UrlList";

function App() {
  const [urls, setUrls] = useState([]);

  const handleShorten = async (data) => {
    try {
      const res = await fetch("http://localhost:8080/shorturls", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
      });
      if (!res.ok) throw new Error("Failed to shorten URL");
      const result = await res.json();

      // Add creation timestamp & click info for frontend
      const newUrl = {
        originalUrl: data.url,
        shortLink: result.shortLink,
        createdAt: new Date().toISOString(),
        expiry: result.expiry,
        clickCount: 0,
        clicks: [],
      };
      setUrls((prev) => [newUrl, ...prev]);
    } catch (err) {
      alert(err.message);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>URL Shortener</h1>
      <ShortenForm onShorten={handleShorten} />
      <UrlList urls={urls} />
    </div>
  );
}

export default App;
