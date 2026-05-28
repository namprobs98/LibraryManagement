package com.librarymanagement.controller;

import com.librarymanagement.service.StorageMode;
import com.librarymanagement.service.StorageService;
import org.springframework.stereotype.Controller;

@Controller
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    public void switchMode(StorageMode mode) {
        storageService.switchMode(mode);
    }

    public StorageMode getMode() {
        return storageService.getCurrentMode();
    }
}
