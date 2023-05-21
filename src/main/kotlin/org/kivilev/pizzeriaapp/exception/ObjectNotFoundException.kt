/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.exception

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = NOT_FOUND, reason = "Object not found")
class ObjectNotFoundException(errorMessage: String) : RuntimeException(errorMessage)
