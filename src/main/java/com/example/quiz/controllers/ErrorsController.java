package com.example.quiz.controllers;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Получение информации об ошибке
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // Обработка различных статус-кодов ошибок
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404"; // Возвращаем страницу с ошибкой 404
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500"; // Возвращаем страницу с ошибкой 500
            }
        }
        return "error"; // Возвращаем общую страницу ошибки
    }

}
