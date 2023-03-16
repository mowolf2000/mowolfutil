/**
 * @file
 * @copyright (C) Copyright IBM NGNCC 2004-2022
 * @date      03.01.2022
 * @author    mwolf
 * @brief     allgemeine String-Makros und -Funktionen
 */

#include "strutil.h"

#include <stdarg.h>
#include <cstring>

static int mw2000_strncat(char* target, size_t size, const char* source, long source_len);
static int mw2000_strncat_m(char* target, size_t size, size_t count, va_list args);

/** copies into target. */
int mw_str_copy(char* target, size_t size, const char* source)
{
    if (target != nullptr && size != 0) {
        target[0] = 0;
    }
    return mw2000_strncat(target, size, source, source != nullptr ? strnlen(source, size) : 0);
}

char* mw_str_ncopy(char* target, size_t size, const char* source, size_t source_len)
{
    if (target != nullptr && size != 0) {
        target[0] = 0;
    }
    return mw2000_strncat(target, size, source, source_len);
}

/** copies and concatenates into target. */
char* mw_str_m_copy(char* target, size_t size, size_t count, const char* source, ...)
{
    if (target != nullptr && size != 0) {
        target[0] = 0;
    }

    va_list vl;
    va_start(vl, count);
    mw2000_strncat_m(target, size, count, vl);
    va_end(vl);
    return target;
}

/** append to the target. */
char* mw_str_cat(char* target, size_t size, const char* source)
{
    return mw2000_strncat(target, size, source, source != nullptr ? strnlen(source, size) : 0);
}

char* mw_str_ncat(char* target, size_t size, const char* source, size_t source_len)
{
    return mw2000_strncat(target, size, source, source_len);
}

/** concatenates and append to the target. */
char* mw_str_m_cat(char* target, size_t size, size_t count, const char* source, ...)
{
    va_list vl;
    va_start(vl, count);
    mw2000_strncat_m(target, size, count, vl);
    va_end(vl);
    return target;
}

static int mw2000_strncat(char* target, size_t size, const char* source, long source_len)
{
    if (target == nullptr || size == 0) {
        return -1;
    }

    size_t start = strnlen(target, size);
    if (size - start <= source_len) {
        strncpy(target + start, source, size - start - 1);
        target[size - 1] = 0;
        return -1;
    } else {
        strncpy(target + start, source, source_len);
        target[start + source_len] = 0;
        return 0;
    }
}

static int mw2000_strncat_m(char* target, size_t size, size_t count, va_list args)
{
    if (target == nullptr || size == 0) {
        return -1;
    }

    size_t start = strnlen(target, size);
    for (size_t i = 0; i < count; i++) {
        const char* source = va_arg(args, const char*);    // @suppress("C-Style cast instead of C++ cast")
        size_t source_len = source != nullptr ? strnlen(source, size) : 0;

        if (size - start <= source_len) {
            strncpy(target + start, source, size - start - 1);
            target[size - 1] = 0;
            return -1;
        }
        strncpy(target + start, source, source_len);
        target[start + source_len] = 0;
        start += source_len;
    }
    return 0;
}
