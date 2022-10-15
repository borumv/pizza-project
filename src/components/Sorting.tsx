import React from 'react';
import { useSelector, useDispatch } from 'react-redux';

import { setOrder } from '../redux/slices/filterSlice';
import { sortSelector } from '../redux/slices/filterSlice';

export enum Sort {
  RATING_DESC = 'rating',
  RATING_ASC = '-rating',
  TITLE_DESC = 'title',
  TITLE_ASC = '-title',
  PRICE_DESC = 'price',
  PRICE_ASC = '-price',
}
export type SortProperty = {
  name: string;
  sortProperty: Sort;
};

export const listValues: SortProperty[] = [
  { name: 'популярности по возрастанию', sortProperty: Sort.RATING_ASC },
  { name: 'цене по возрастанию', sortProperty: Sort.PRICE_ASC },
  { name: 'алфавиту по возрастанию', sortProperty: Sort.TITLE_ASC },
  { name: 'популярности по убыванию', sortProperty: Sort.RATING_DESC },
  { name: 'цене по убыванию', sortProperty: Sort.PRICE_DESC },
  { name: 'алфавиту по убыванию', sortProperty: Sort.TITLE_DESC },
];

export const Sorting: React.FC<any> = ({ openState, setOpenState, sortingRef }) => {
  const sortState = useSelector(sortSelector);
  console.log('sortstate - ', sortState);
  const changeValue: SortProperty = sortState.sort;
  const dispatch = useDispatch();

  const onChangeValue = (item: SortProperty) => {
    dispatch(setOrder(item));
  };

  return (
    <div className="sort" ref={sortingRef}>
      <div className="sort__label">
        <svg
          width="10"
          height="6"
          viewBox="0 0 10 6"
          fill="none"
          xmlns="http://www.w3.org/2000/svg">
          <path
            d="M10 5C10 5.16927 9.93815 5.31576 9.81445 5.43945C9.69075 5.56315 9.54427 5.625 9.375 5.625H0.625C0.455729 5.625 0.309245 5.56315 0.185547 5.43945C0.061849 5.31576 0 5.16927 0 5C0 4.83073 0.061849 4.68424 0.185547 4.56055L4.56055 0.185547C4.68424 0.061849 4.83073 0 5 0C5.16927 0 5.31576 0.061849 5.43945 0.185547L9.81445 4.56055C9.93815 4.68424 10 4.83073 10 5Z"
            fill="#2C2C2C"
          />
        </svg>
        <b>Сортировка по:</b>
        <span onClick={() => setOpenState(!openState)}>{changeValue.name}</span>
      </div>
      {openState && (
        <div className="sort__popup">
          <ul>
            {listValues.map((item, i: number) => {
              return (
                <li
                  key={i}
                  onClick={() => onChangeValue(item)}
                  className={changeValue.sortProperty === item.sortProperty ? 'active' : ''}>
                  {item.name}
                </li>
              );
            })}
          </ul>
        </div>
      )}
    </div>
  );
};
