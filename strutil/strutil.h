/**
 * @file
 * @copyright (C) Copyright mwolf2000 2023
 * @date      10.03.2023
 * @author    mwolf2000
 *
 * Common string functions
 */
/**
 * @file
 * @copyright (C) Copyright IBM NGNCC 2004-2022
 * @date      03.01.2022
 * @author    mwolf
 * @brief     allgemeine String-Makros und -Funktionen
 */

#ifndef STRUTIL_STRUTIL_H
#define STRUTIL_STRUTIL_H

#include <stddef.h>

#ifdef __cplusplus
extern "C" {
#endif

#ifdef MW_BUF_SIZE
#undef MW_BUF_SIZE
#endif

/**
 * @brief passes buffer and size of buffer as parameters. The size of the buffer must be known by the compiler at this point.
 * @param a     buffer
 */
#define MW_BUF_SIZE(a)            (a), (a == nullptr ? 0 : sizeof(a))

/**
 * @brief copies the source into target buffer
 * @param target      target buffer
 * @param size        size of target buffer
 * @param source      source string
 * @param source_len  length of source string
 * @param count       number of following strings
 *
 * @return 0 if buffer is successful
 *        -1 if buffer overflow happened, value is target is cut but 0 terminated
 */

/** copies into target. */
int mw_str_copy(char* target, size_t size, const char* source);
int mw_str_ncopy(char* target, size_t size, const char* source, size_t source_len);

/** copies and concatenates into target. */
int mw_str_m_copy(char* target, size_t size, size_t count, const char* source, ...);

/** append to the target. */
int mw_str_cat(char* target, size_t size, const char* source);
int mw_str_ncat(char* target, size_t size, const char* source, size_t source_len);

/** concatenates append to the target. */
int mw_str_m_cat(char* target, size_t size, size_t count, const char* source, ...);

#ifdef __cplusplus
}    // end of extern "C"
#endif

//#ifdef __cplusplus
//
//#include <exception>
//#include <string>
//
//
//
//#endif

#endif /* STRUTIL_STRUTIL_H */
