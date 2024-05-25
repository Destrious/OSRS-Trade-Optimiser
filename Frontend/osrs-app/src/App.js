import './App.css';
import Navbar from "./components/Navbar";
import {Route, Routes} from "react-router-dom";
import HomePage from "./pages/HomePage";
import LogInPage from "./pages/LogInPage";
import TradePage from "./pages/TradePage";

function App() {
    return (
        <div className="App">
            <Navbar className="sticky-navbar"/>

            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/login" element={<LogInPage />} />
                <Route path="/trade" element={<TradePage />} />
            </Routes>
        </div>
  );
}

export default App;
