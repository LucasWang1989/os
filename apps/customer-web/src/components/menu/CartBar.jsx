import React from "react";

export default function CartBar({ totalMoney, totalCount, onConfirm }) {
    return (
        <div className="cart ng-scope">
            <div className="r">
                <a id="cart-link1" onClick={(e) => { e.preventDefault(); onConfirm(); }}>
                    Confirm
                </a>
            </div>

            <div className="l ng-binding">
                $<label id="totalmoney">{totalMoney}</label>
            </div>

            <i id="totalcartnumber" className="ng-binding cartnum">
                {totalCount}
            </i>

            <a
                id="cart-link2"
                onClick={(e) => { e.preventDefault(); onConfirm(); }}
            >
                <img src="assets/img/shop_cart01.png" />
            </a>
        </div>
    );
}
