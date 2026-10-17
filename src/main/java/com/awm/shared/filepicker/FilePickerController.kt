package org.connecttag.features.filepicker

import java.io.File

public class FilePickerController(
    private val showHiddenItems: Boolean = false,
) {
    public fun load(path: String, title: String = "Select file"): FilePickerState {
        val directory = File(path).let { file -> if (file.isDirectory) file else file.parentFile }
        if (directory == null || !directory.exists()) {
            return FilePickerState(title = title, currentPath = path, errorMessage = "Directory does not exist")
        }
        if (!directory.isDirectory) {
            return FilePickerState(title = title, currentPath = directory.absolutePath, errorMessage = "Path is not a directory")
        }

        val items = directory.listFiles()
            .orEmpty()
            .asSequence()
            .filter { showHiddenItems || !it.name.startsWith('.') }
            .sortedWith { first, second ->
                when {
                    first.isDirectory && !second.isDirectory -> -1
                    !first.isDirectory && second.isDirectory -> 1
                    else -> first.name.compareTo(second.name, ignoreCase = true)
                }
            }
            .map { file ->
                FilePickerItem(
                    path = file.absolutePath,
                    name = file.name,
                    isDirectory = file.isDirectory,
                    sizeBytes = file.takeIf { it.isFile }?.length(),
                    modifiedAtMillis = file.lastModified().takeIf { it > 0L },
                )
            }
            .toList()

        return FilePickerState(
            title = title,
            currentPath = directory.absolutePath,
            items = items,
        )
    }
}
