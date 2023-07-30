import React, { useEffect, useState } from 'react';
import PhoneInput from 'react-phone-number-input/input';
import { E164Number } from 'libphonenumber-js/core';
import { useSelector, useDispatch } from 'react-redux';
import { Cart } from './Cart';
import { ICart } from './Cart';
import axios from 'axios';
import { CartPopUpResult } from './CartPopUpResult';

export type ICartOrder = {
  pizzaId: number;
  type: string;
  size: number;
  count: number;
};

interface Order {
  orderedPizzas: ICartOrder[];
  finalPrice: number;
  phoneNumber: string;
}

export const PopUp: React.FC<any> = ({ showPopup, togglePopup }) => {
  const items = useSelector((state: any) => state.cartReducer.items);
  const totalCost: number = useSelector((state: any) => state.cartReducer.totalCost);
  const totalCount = useSelector((state: any) => state.cartReducer.totalCount);
  const [value, setValue] = useState<string>('20');
  const [errorOrder, setErrorOrder] = useState<boolean>(false);
  const [orderOk, setOrderOk] = useState<boolean>(false);

  const payOrder = async (orderData: Order) => {
    const response = await axios.post<Order | boolean>(
      `http://localhost:8080/api/order`,
      orderData,
    );
    return response.data;
  };

  const handlePay = async () => {
    const orderData: Order = {
      orderedPizzas: items.map((item: ICart) => {
        return {
          pizzaId: item.id,
          type: item.type,
          size: item.size,
          count: item.count,
        };
      }),

      finalPrice: totalCost,
      phoneNumber: value,
    };

    // Вызываем функцию payOrder и обрабатываем результат
    try {
      const result = await payOrder(orderData);
      console.log('Заказ успешно оплачен:', result);
      setOrderOk(true);
      // Здесь можно добавить логику для обработки успешной оплаты, например, показать сообщение об успехе
    } catch (error) {
      console.error('Ошибка при оплате заказа:', error);
      setErrorOrder(true);
      // Здесь можно добавить логику для обработки ошибки, например, показать сообщение об ошибке
    }
  };

  React.useEffect(() => {}, [setErrorOrder, setOrderOk]);
  if (!showPopup) {
    return null;
  }

  return (
    <>
      {errorOrder && <CartPopUpResult isError={true} />}
      {orderOk && <CartPopUpResult isError={false} />}

      {!errorOrder && !orderOk && showPopup && (
        <div
          className="modal fade"
          id="exampleModal"
          //tabIndex={-1}
          role="dialog"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true">
          <div className="modal-dialog" role="document">
            <div className="modal-content">
              <div className="modal-body">
                <div className="column" id="main">
                  <h1>регистрация заказа</h1>
                  <p>Цена: {totalCost}</p>
                  <p>Количество: {totalCount}</p>
                  <h3>Введите номер телефона для оформления заказа</h3>

                  <br />
                  <form>
                    <div className="phone-input-container">
                      <PhoneInput
                        country="US"
                        value={value}
                        onChange={(newValuse: E164Number) => setValue(newValuse)}
                      />
                    </div>
                    <br />
                    <div className="button pay-btn" onClick={handlePay}>
                      <span>Оплатить сейчас</span>
                    </div>
                  </form>
                </div>

                <div
                  style={{
                    display: 'block',
                    overflow: 'hidden',
                  }}>
                  {/* SVG code */}
                  <svg
                    width="67px"
                    height="100%"
                    viewBox="0 0 67 578"
                    version="1.1"
                    xmlns="http://www.w3.org/2000/svg"
                    xmlnsXlink="http://www.w3.org/1999/xlink">
                    <title>Path</title>
                    <desc>Created with Sketch.</desc>
                    <g id="Page-1" stroke="none" strokeWidth="1" fill="none" fillRule="evenodd">
                      <path
                        d="M11.3847656,0 C-7.44726562,36.7213542 5.14322917,126.757812 49.15625,270.109375 C70.9827986,341.199016 54.8877465,443.829224 0.87109375,578 L67,578 L67,0 L11.3847656,0 Z"
                        id="Path"
                        fill="#F9BC35"></path>
                    </g>
                  </svg>
                </div>
                <div className="column" id="secondary">
                  <div className="sec-content">
                    <ul>
                      {items.map((item: any, index: number) => {
                        console.log(item.imageUrl);
                        return (
                          <li key={index}>
                            <img src={item.imageUrl} alt="pizza" />
                            <p>
                              {item.title}, Тип теста: {item.type}, Размер: {item.size}
                            </p>
                            <h5>Количество: {item.count}</h5>
                          </li>
                        );
                      })}
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          {/* Close button */}
        </div>
      )}
      <div className="button close-btn" onClick={togglePopup}>
        <span className="close-icon">X</span>
      </div>
    </>
  );
};
