package org.connecttag.features.filepicker

public enum class FilePickerMode {
    FILE,
    DIRECTORY,
}

public data class FilePickerItem(
    public val path: String,
    public val name: String,
    public val isDirectory: Boolean,
    public val sizeBytes: Long? = null,
    public val modifiedAtMillis: Long? = null,
)

public data class FilePickerState(
    public val title: String = "Select file",
    public val currentPath: String = "",
    public val items: List<FilePickerItem> = emptyList(),
    public val selectedPath: String? = null,
    public val isLoading: Boolean = false,
    public val errorMessage: String? = null,
)

public data class FilePickerConfig(
    public val mode: FilePickerMode = FilePickerMode.FILE,
    public val showHiddenItemsToggle: Boolean = false,
    public val showCreateFolderAction: Boolean = false,
    public val confirmText: String = "OK",
    public val cancelText: String = "Cancel",
    public val emptyMessage: String = "No items found",
)

public sealed interface FilePickerEvent {
    public data object BackClicked : FilePickerEvent
    public data object CancelClicked : FilePickerEvent
    public data object ConfirmClicked : FilePickerEvent
    public data object CreateFolderClicked : FilePickerEvent
    public data object ToggleHiddenItemsClicked : FilePickerEvent
    public data class ItemClicked(public val item: FilePickerItem) : FilePickerEvent
}
