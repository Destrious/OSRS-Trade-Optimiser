import React from "react";
import PricingTableComponent from "../components/PricingTableComponent";

const HomePage = () => {
    return (
        <div style={{ margin: '20px' }}>
            <h1>Profitable Items</h1>
            <PricingTableComponent />
        </div>
    );
};

export default HomePage;
