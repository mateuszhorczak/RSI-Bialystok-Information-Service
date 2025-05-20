# Bialystok Information Service

The Bialystok Information Service is a system designed to manage and display local events in the city of Białystok. It provides a seamless way for users to explore, add, and manage events through a robust web service and an intuitive web-based client application. This project was developed as an academic endeavor, combining modern web technologies with a focus on usability and functionality.

The system consists of two main components:
1. **Web Service** (SOAP-based) – Handles the backend logic and data management for events.
2. **Client Application** (Web-based) – A user-friendly interface for interacting with the event data.

---

## 🛠️ Functional Requirements

The service provides the following features:

1. 📅 **Retrieve Events for a Specific Day** – Fetch all events scheduled for a given date.
2. 📆 **Retrieve Events for a Specific Week** – View events occurring within a selected week.
3. 🔍 **Event Details** – Access detailed information about a specific event.
4. ➕ **Add New Event** – Create a new event with the following fields:
   - **Name** – Event title
   - **Type** – Category or type of event (e.g., cultural, sports, community)
   - **Date** – Event date
   - **Week** – Week number
   - **Month** – Month number
   - **Year** – Year of the event
   - **Description** – Detailed description of the event
5. ✏️ **Edit Existing Event** – Modify details of an existing event.
6. 📄 **Generate Event Report** – Export a summary of events in PDF format.

---

## 🛠️ Tech Stack

The project is built using modern and reliable technologies to ensure scalability and ease of use:

- **Backend**: Java-based SOAP web service for robust and secure event management.
- **Frontend**: Nuxt.js (Vue.js framework) with Pinia for state management and Nuxt UI as the component library for a polished, accessible user interface.

---

## 🚀 Installation

To set up the project locally, follow these steps:

1. **Install Prerequisites**:
   - Ensure you have [Node.js](https://nodejs.org/) installed (version 18 or higher recommended).
   - Install [pnpm](https://pnpm.io/) globally by running:
     ```bash
     npm install -g pnpm
     ```
   - For the SOAP service, ensure you have [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (version 17 or higher) installed.

2. **Clone the Repository**:
   ```bash
   git@github.com:mateuszhorczak/RSI-Bialystok-Information-Service.git
   ```

3. **Set Up the Client**:
   - Navigate to the client directory:
     ```bash
     cd ClientApp
     ```
   - Install dependencies using pnpm:
     ```bash
     pnpm install
     ```

4. **Generate SSL Certificates**:
   - For secure communication, generate a local SSL certificate using a tool like [mkcert](https://github.com/FiloSottile/mkcert):
     ```bash
     mkcert localhost
     ```
   - Follow the mkcert documentation to install the generated certificates for your development environment.

5. **Set Up the Backend**:
   - Navigate to the server directory:
     ```bash
     cd JavaApp
     ```
   - Build and run the Java SOAP service using your preferred build tool (e.g., Maven or Gradle).

6. **Run the Application**:
   - Start the client application:
     ```bash
     cd ClientApp
     pnpm dev
     ```
   - Start the SOAP service.

7. **Access the Application**:
   - Open your browser and navigate to `https://localhost:3000`.

---

## 📚 Usage

Once the application is running, you can:
- Browse events by day or week using the intuitive web interface.
- Add or edit events with a user-friendly form.
- Export event summaries as PDF reports for offline use.

---

## 👥 Authors

This project was created by:
- [Mateusz Horczak](https://github.com/mateuszhorczak)
- [Anita Jurkowska](https://github.com/nisia289)

Developed as part of an academic project, showcasing skills in full-stack development and system integration.

---

## 📜 License

This project is licensed under the MIT License. See the [LICENSE.txt](LICENSE.txt) file for more details.
