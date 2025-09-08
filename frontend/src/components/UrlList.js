import React from "react";

export default function UrlList({ urls }) {
  return (
    <div>
      {urls.map((u, idx) => (
        <div key={idx} style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}>
          <p><strong>Original:</strong> {u.originalUrl}</p>
          <p>
            <strong>Shortened:</strong>{" "}
            <a href={u.shortLink} target="_blank" rel="noreferrer">{u.shortLink}</a>
          </p>
          <p><strong>Created At:</strong> {u.createdAt}</p>
          <p><strong>Expires At:</strong> {u.expiry}</p>
          <p><strong>Total Clicks:</strong> {u.clickCount}</p>
          {u.clicks?.length > 0 && (
            <div>
              <strong>Click Details:</strong>
              <ul>
                {u.clicks.map((c, i) => (
                  <li key={i}>
                    {c.timestamp} | Referrer: {c.referrer} | Geo: {c.geoLocation}
                  </li>
                ))}
              </ul>
            </div>
          )}
        </div>
      ))}
    </div>
  );
}
