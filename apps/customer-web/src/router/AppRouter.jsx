// router/AppRouter.jsx
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MenuPage from "../pages/MenuPage";
import CartPage from "../pages/CartPage";
import CartEmptyPage from "../pages/CartEmptyPage.jsx";

export default function AppRouter() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/menu" element={<MenuPage />} />
                <Route path="/cart" element={<CartPage />} />
                <Route path="/cart/empty" element={<CartEmptyPage />} />
            </Routes>
        </BrowserRouter>
    );
}
