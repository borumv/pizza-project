import React from 'react';
import ReactPaginate from 'react-paginate';
import { useSelector } from 'react-redux';
import { RootState } from '../../redux/store';
import styles from './Paginator.module.scss';
import { setPage } from '../../redux/slices/pizzaSlice';
import { useAppDispatch } from '../../redux/hook';

const Paginator: React.FC<any> = ({ pageNumber, setPageNumber, countElements, activeCategory }) => {
  const { pageInfo } = useSelector((state: RootState) => state.pizzaReducer.data.items);
  const dispatch = useAppDispatch();

  React.useEffect(() => {
    dispatch(setPage(0));
  }, [activeCategory]);

  return (
    <div>
      <ReactPaginate
        className={styles.root}
        breakLabel="..."
        nextLabel=">"
        previousLabel="<"
        onPageChange={(e) => {
          setPageNumber(e.selected);
          dispatch(setPage(pageNumber));
          console.log(e);
        }}
        pageRangeDisplayed={4}
        pageCount={pageInfo.totalPages}
        forcePage={pageNumber}
      />
    </div>
  );
};

export default Paginator;
