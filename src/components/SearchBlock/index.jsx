import styles from './SearchBlock.module.scss'
import React from 'react'
import { useContext } from 'react'
import { SearchContext } from '../../App'

const SearchBlock = () => {
  const {searchValue, setSearchValue} = useContext(SearchContext)
  return (
    <div className={styles.root}>   
        <input className={styles.input} placeholder="Find pizza ..." onChange={e=> setSearchValue(e.target.value)}/>
    </div>
  )
}
export default SearchBlock;