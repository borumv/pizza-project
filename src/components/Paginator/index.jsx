import React from 'react'
import ReactPaginate from 'react-paginate';
import styles from './Paginator.module.scss'
import { SearchContext } from '../../App';

const Paginator = ({pageNumber, setPageNumber, countElements, activeCategory }) => {
  
  const {searchValue, } = React.useContext(SearchContext)

  React.useEffect(()=>{
      setPageNumber(0)
  },[searchValue, activeCategory])

  return (
    <div>
      <ReactPaginate
        className={styles.root}
              breakLabel="..."
              nextLabel=">"
              previousLabel="<"
              onPageChange={e => {setPageNumber(e.selected)
              console.log(e)}}
              pageRangeDisplayed={5}
              pageCount={countElements}
              forcePage={pageNumber}

              
            />
    </div>
  )
}

export default Paginator