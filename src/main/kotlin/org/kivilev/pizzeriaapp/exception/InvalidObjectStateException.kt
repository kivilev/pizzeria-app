package org.kivilev.pizzeriaapp.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Object is not in appropriate state")
class InvalidObjectStateException(errorMessage: String) : RuntimeException(errorMessage)
