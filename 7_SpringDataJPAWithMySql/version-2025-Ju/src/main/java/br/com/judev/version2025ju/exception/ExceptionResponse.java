package br.com.judev.version2025ju.exception;

import java.util.Date;

public record ExceptionResponse(Date timestanp, String message, String details) {
}
