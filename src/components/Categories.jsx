import React from 'react';
import { useCallback } from 'react';

import { useSelector, useDispatch } from 'react-redux';
import { setActiveCategory } from '../redux/slices/filterSlice';

export default function Categories({ categories }) {
  const [activeValue, setActiveValue] = React.useState(0);
  const activeCategory = useSelector((state) => state.filterReducer.activeCategory);
  const dispatch = useDispatch();

  React.useEffect(() => {
    setActiveValue(activeCategory);
  }, [activeCategory]);

  const onChangeCategory = useCallback((index) => {
    dispatch(setActiveCategory(index));
  }, []);

  const classnNameCreator = (index) => {
    if (Number(activeValue) === index) {
      return 'active';
    } else {
      return '';
    }
  };

  return (
    <>
      <div className="categories">
        <ul>
          {categories.map((item, index) => {
            return (
              <li
                key={index}
                onClick={() => {
                  onChangeCategory(index);
                }}
                className={classnNameCreator(index)}>
                {item}
              </li>
            );
          })}
        </ul>
      </div>
    </>
  );
}
