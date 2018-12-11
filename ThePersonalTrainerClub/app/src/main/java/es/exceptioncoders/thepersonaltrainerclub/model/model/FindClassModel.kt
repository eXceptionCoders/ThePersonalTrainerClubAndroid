package es.exceptioncoders.thepersonaltrainerclub.model.model

data class FindClassesModel (
        val total: Int,
        val classes: Array<ClassModel>
)