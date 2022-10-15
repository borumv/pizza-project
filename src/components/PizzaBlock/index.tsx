import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { addItem } from '../../redux/slices/cartSlice';
import { IPizza } from '../../redux/slices/pizzaSlice';

type IPizzaProps = {
  id: number;
  title: string;
  imageUrl: string[];
  sizeAndCost: [{ size: number; cost: number }];
  types: string[];
};

export const Pizza: React.FC<IPizza> = ({ id, title, sizeAndCost, imageUrl, types }) => {
  const [indexChangedSize, setindexChangedSize] = React.useState(0);
  const [indexType, setIndexType] = React.useState(0);
  const items = useSelector((state: any) => state.cartReducer.items);
  let count = items
    .filter((item: { id: number }) => item.id === id)
    .reduce((prev: number, next: { count: number }) => {
      return prev + next.count;
    }, 0);

  const dispatch = useDispatch();
  let pizza = {
    id,
    title,
    imageUrl: imageUrl[0],
    size: sizeAndCost[indexChangedSize].size,
    cost: sizeAndCost[indexChangedSize].cost,
    type: types[indexType],
    count: 1,
  };

  return (
    <div className="pizza-block">
      <img className="pizza-block__image" src={imageUrl[0]} alt="Pizza" />
      <h4 className="pizza-block__title">{title}</h4>
      <div className="pizza-block__selector">
        <ul>
          {types.map((item, index) => {
            return (
              <li
                key={index}
                onClick={() => setIndexType(index)}
                className={index === indexType ? 'active' : ''}>
                {item}
              </li>
            );
          })}
        </ul>
        <ul>
          {sizeAndCost.map((item, index) => {
            return (
              <li
                key={index}
                onClick={() => setindexChangedSize(index)}
                className={indexChangedSize === index ? 'active' : ''}>
                {item.size} см.
              </li>
            );
          })}
        </ul>
      </div>
      <div className="pizza-block__bottom">
        <div className="pizza-block__price">от {sizeAndCost[indexChangedSize].cost} ₽</div>
        <button
          className="button button--outline button--add"
          onClick={() => dispatch(addItem(pizza))}>
          <svg
            width="12"
            height="12"
            viewBox="0 0 12 12"
            fill="none"
            xmlns="http://www.w3.org/2000/svg">
            <path
              d="M10.8 4.8H7.2V1.2C7.2 0.5373 6.6627 0 6 0C5.3373 0 4.8 0.5373 4.8 1.2V4.8H1.2C0.5373 4.8 0 5.3373 0 6C0 6.6627 0.5373 7.2 1.2 7.2H4.8V10.8C4.8 11.4627 5.3373 12 6 12C6.6627 12 7.2 11.4627 7.2 10.8V7.2H10.8C11.4627 7.2 12 6.6627 12 6C12 5.3373 11.4627 4.8 10.8 4.8Z"
              fill="white"
            />
          </svg>
          <span>Добавить</span>
          {count > 0 && <i>{count}</i>}
        </button>
      </div>
    </div>
  );
};
