package com.home.taskmanager.exceptions

class NoSuchProcessException(pid: String) : RuntimeException("No such process")