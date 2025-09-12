
## 📌 Project Overview
This project is part of the **Campus Hiring Evaluation** for **Afford Medical Technologies Pvt. Ltd.**.  
The task was to design and implement a **Full Stack URL Shortener Application** consisting of:
1. A **Java-based Microservice** backend.
2. A **React frontend** for user interaction.
3. Integration with **custom logging middleware** (no console logs or inbuilt loggers allowed).

The system allows users to **shorten URLs, manage validity, track analytics, and view usage statistics**.

---

## 🛠️ Tech Stack
### Backend (Microservice)
- **Language:** Java / Spring Boot (Microservice architecture)
- **Database:** In-memory (or SQL/NoSQL as needed)
- **Logging:** Custom Middleware (mandatory as per evaluation instructions)
- **Features:**
  - RESTful API endpoints
  - URL shortening with optional custom shortcodes
  - Expiry management (default: 30 minutes)
  - Analytics tracking (click count, referrer, timestamp, geo-location)
  - Robust error handling (invalid inputs, expired links, collisions)

### Frontend (React Web App)
- **Framework:** React.js
- **Styling:** Material UI (mandatory requirement)  
- **Features:**
  - URL Shortener Page (shorten up to 5 URLs at once)
  - Client-side validation (valid URL, integer validity)
  - Display of shortened links + expiry dates
  - Statistics Page (list of URLs with analytics, clicks, referrers, locations)

---

## ⚙️ Backend API Endpoints

### 1️⃣ Create Short URL
- **POST** `/shorturls`
- **Request Body:**
```json
{
  "url": "https://very-long-url.com/page",
  "validity": 30,
  "shortcode": "abcd1"
}
