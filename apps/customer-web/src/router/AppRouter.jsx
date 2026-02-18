// router/AppRouter.jsx
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MenuPage from "../pages/MenuPage.jsx";
import CartPage from "../pages/CartPage.jsx";
import CartEmptyPage from "../pages/CartEmptyPage.jsx";
import OrderReceiptPage from "../pages/OrderReceiptPage.jsx";

export default function AppRouter() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/menu" element={<MenuPage />} />
                <Route path="/cart" element={<CartPage />} />
                <Route path="/cart/empty" element={<CartEmptyPage />} />
                <Route path="/receipt" element={<OrderReceiptPage />} />
            </Routes>
        </BrowserRouter>
    );
}
