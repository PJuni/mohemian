package app.mohemian.domain.useCase.base

sealed class UseCaseParam {

    sealed class Flickr : UseCaseParam() {
        data class SearchPhoto(val searchText: String) : Flickr()
    }
}
