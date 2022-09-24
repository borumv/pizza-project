import styles from './SearchBlock.module.scss'
import React from 'react'
import {useDispatch } from 'react-redux'
import {setSearchValue} from '../../redux/slices/filterSlice'
import { debounce } from "lodash"

const SearchBlock = () => {
 // const {searchValue, setSearchValue} = useContext(SearchContext)
 const dispatch = useDispatch()
  const [searchValues, setSearchValues] = React.useState('');
  const inputRef = React.useRef();
  
  const onClickClear = () => {
    inputRef.current.focus()
    setSearchValues('');  
    dispatch(setSearchValue(''))
  };


  const onChangeInput = (event) => {
    console.log('inputRef - ', inputRef)
    setSearchValues(event.target.value);
    debounceFn(event.target.value);
  };

  const debounceFn = React.useCallback(
    debounce((str)=>{
      dispatch(setSearchValue(str))
    }, 500),[]
  )

  return (
    <div className={styles.root}>
    <svg
      className={styles.icon}
      enableBackground="new 0 0 32 32"
      id="EditableLine"
      version="1.1"
      viewBox="0 0 32 32"
      xmlns="http://www.w3.org/2000/svg">
      <circle
        cx="14"
        cy="14"
        fill="none"
        id="XMLID_42_"
        r="9"
        stroke="#000000"
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeMiterlimit="10"
        strokeWidth="2"
      />
      <line
        fill="none"
        id="XMLID_44_"
        stroke="#000000"
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeMiterlimit="10"
        strokeWidth="2"
        x1="27"
        x2="20.366"
        y1="27"
        y2="20.366"
      />
    </svg>
    <input
      ref={inputRef}
      value={searchValues}
      onChange={onChangeInput}
      className={styles.input}
      placeholder="Поиск пиццы..."
    />
    {searchValues && (
      <svg
        onClick={onClickClear}
        className={styles.clearIcon}
        viewBox="0 0 20 20"
        xmlns="http://www.w3.org/2000/svg">
        <path d="M10 8.586L2.929 1.515 1.515 2.929 8.586 10l-7.071 7.071 1.414 1.414L10 11.414l7.071 7.071 1.414-1.414L11.414 10l7.071-7.071-1.414-1.414L10 8.586z" />
      </svg>
    )}
  </div>
  )
}
export default SearchBlock;