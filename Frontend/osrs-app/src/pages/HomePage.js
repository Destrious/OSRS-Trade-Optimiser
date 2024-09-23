import React from "react";
import PricingTableComponent from "../components/PricingTableComponent";

const HomePage = () => {
    return (
        <div style={{ margin: '20px' }}>
            <h1>Current Item Prices</h1>
            <p>Click on the "Log in" button in the top right corner to log in.</p>

            <PricingTableComponent />
        </div>
    );
};

export default HomePage;
