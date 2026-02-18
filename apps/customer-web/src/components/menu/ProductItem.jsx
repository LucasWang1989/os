import React from "react";
import QuantityControl from "../common/QuantityControl.jsx";

export default function ProductItem({ product, qty, onAdd, onRemove }) {
    const priceDollars = ((product.price || 0) / 100).toFixed(2);

    return (
        <li
            className="on"
            price={priceDollars}
            cart="2"
            productid={product.id}
        >
            <div className="img view">
                <img
                    className="pimgpath"
                    src={`assets/${product.imagePath}`}
                    alt={product.name}
                />
            </div>

            <p className="t">{product.name}</p>
            <p className="price">${priceDollars}</p>

            <div className="showaddcart meshop">
                <QuantityControl
                    value={qty}
                    onRemove={() => onRemove(product.id)}
                    onAdd={() => onAdd(product.id, product)}
                />
            </div>

            {/* 你 JSP 的 detail 区域先保留结构，后续再做弹层 */}
            {/*<div className="detail">*/}
            {/*    <div className="back">*/}
            {/*        <img src="img/close.png" className="img-responsive" alt="..." />*/}
            {/*    </div>*/}
            {/*    <div className="title"></div>*/}
            {/*    <div className="img">*/}
            {/*        <img src="img/tof/timg-6.jpg" className="img-responsive" alt="..." />*/}
            {/*    </div>*/}
            {/*    <div className="desc"></div>*/}
            {/*</div>*/}
        </li>
    );
}
