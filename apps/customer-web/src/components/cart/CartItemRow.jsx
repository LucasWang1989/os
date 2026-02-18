import React from "react";
import QuantityControl from "../common/QuantityControl.jsx";

export default function CartItemRow({ product, qty, onAdd, onRemove }) {
    const priceDollars = ((product.price || 0) / 100).toFixed(2);

    return (
        <li price={priceDollars} className="on" cart="1" productid={product.id}>
            <div className="l">
                <a href="/proDetail.html" target="_blank" rel="noreferrer">
                    <img src={`assets/${product.imagePath}`} className="img-responsive" alt="..." />
                </a>
            </div>

            <div className="r">
                <p className="t">{product.name}</p>
                <p className="price">${priceDollars}</p>

                <div className="showaddcart">
                    <QuantityControl
                        value={qty}
                        onRemove={onRemove}
                        onAdd={onAdd}
                    />
                </div>
            </div>
        </li>
    );
}
