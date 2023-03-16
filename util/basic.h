/**
 * @file
 * @copyright (C) Copyright mwolf2000 2023
 * @date      10.03.2023
 * @author    mwolf2000
 *
 * Basic defines
 */

#ifndef UTIL_BASIC_H
#define UTIL_BASIC_H

#ifndef __cplusplus
#ifdef nullptr
#undef nullptr
#endif
/** @brief defines nullptr analog to C++ */
#define nullptr                 NULL
#endif

#ifdef MW_DIM
#undef __MW2000_MEMBER_ADDR
#undef MW_DIM
#undef MW_SIZEOF_MEMBER
#undef MW_DIM_MEMBER
#endif

#ifdef __cplusplus
#define __MW2000_MEMBER_ADDR(t,m)   t::m
#else
#define __MW2000_MEMBER_ADDR(t,m)   ((t*)NULL)->m
#endif

/**
 * @brief calculates the dimension of an array
 * @param a  array to analyze. The compiler must know the size of the array at that point.
 */
#define MW_DIM(a)                 (sizeof(a) / sizeof((a)[0]))

/**
 * @brief calculates the size of an class or struct attribute
 * @param t  type of class or struct
 * @param m  attribute member of class or struct
 */
#define MW_SIZEOF_MEMBER(t,m)     sizeof(__MW2000_MEMBER_ADDR(t,m))

/**
 * @brief calculates the size of an array attribute of a class or struct
 * @param t  type of class or struct
 * @param m  attribute member of class or struct
 */
#define MW_DIM_MEMBER(t,m)        MW_DIM(__MW2000_MEMBER_ADDR(t,m))

#ifdef MW_BETWEEN
#undef MW_BETWEEN
#endif

/**
 * @brief tests if a values is between the left and the right border.
 * @param l     left border of the interval
 * @param cl    compare operation to the left (like < or <=)
 * @param v     to validate value
 * @param cr    compare operation to the right (like < or <=)
 * @param r     right border of the interval
 */
#define MW_BETWEEN(l,cl,v,cr,r)   (((l) cl (v)) && ((v) cr (r)))

#ifdef MW_MAX
#undef MW_MAX
#undef MW_MIN
#endif
/**
 * @brief pass the maximum or minimum value of two values
 * @param a     1st value
 * @param b     2nd value
 */
#define MW_MAX(a,b)               (((a) > (b)) ? (a) : (b))
#define MW_MIN(a,b)               (((a) < (b)) ? (a) : (b))

#ifdef MW_FUNCTION
#undef MW_FUNCTION
#endif

/** @brief prepared function name */
#ifdef __cplusplus
#define MW_FUNCTION  __PRETTY_FUNCTION__
#else
#define MW_FUNCTION  __func__
#endif

#ifdef MW_CURR_POS
#undef MW_CURR_POS
#undef MW_POS_PARAM
#undef MW_POS_PARAM_PASS
#endif

/** @brief current code position in file, function and line */
#define MW_CURR_POS               __FILE__,              MW_FUNCTION,               __LINE__

/** @brief code position as function parameters */
#define MW_POS_PARAM              const char *_pos_file, const char *_pos_function, int _pos_line

/** @brief passing received code position to another function. */
#define MW_POS_PARAM_PASS         _pos_file,             _pos_function,             _pos_line

#endif /* UTIL_BASIC_H */
