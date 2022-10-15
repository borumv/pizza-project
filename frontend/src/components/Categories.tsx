import React from 'react';
import { useCallback } from 'react';

import { useSelector, useDispatch } from 'react-redux';
import { setActiveCategory } from '../redux/slices/filterSlice';

type ICategoriesProps = {
  categories: string[];
  onchangeCategory: (index: number) => void;
};

export const Categories: React.FC<ICategoriesProps> = ({ categories, onchangeCategory }) => {
  const [activeValue, setActiveValue] = React.useState(0);
  const activeCategory = useSelector((state: any) => state.filterReducer.activeCategory);

  React.useEffect(() => {
    setActiveValue(activeCategory);
  }, [activeCategory]);

  const classnNameCreator = (index: number) => {
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
          {categories.map((item: string, index: number) => {
            return (
              <li
                key={index}
                onClick={() => {
                  onchangeCategory(index);
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
};
