import React from "react";

export default function CartCheckoutBar({ totalMoney, disabled, onCheckout }) {
    return (
        <div className="cart ng-scope">
            <div className="r">
                <a
                    href="/"
                    onClick={(e) => {
                        e.preventDefault();
                        if (!disabled) onCheckout();
                    }}
                    style={disabled ? { opacity: 0.5, pointerEvents: "none" } : null}
                >
                    Checkout
                </a>
            </div>

            <div className="l ng-binding c">
                $<label id="totalmoney">{totalMoney}</label>
            </div>
        </div>
    );
}
