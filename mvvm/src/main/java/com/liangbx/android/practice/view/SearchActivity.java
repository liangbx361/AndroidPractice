package com.liangbx.android.practice.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liangbx.android.practice.databinding.SearchViewModel;

/**
 * Author liangbx
 * Date 2016/1/3
 */
public class SearchActivity extends AppCompatActivity {

    private SearchViewModel mSearchViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mSearchViewModel = DataBindingUtil.setContentView(this, R.layout.activity_search);
//        initSearch();
    }

    private void initSearch() {
//        RxTextView.textChanges(mSearchViewModel.etKeyword)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .debounce(300, TimeUnit.MILLISECONDS)
//                .filter(keyword -> keyword.length() > 0)
//                .switchMap(charSequence -> {
//                    System.out.println("=== " + charSequence);
//                    return App.getApplication(SearchActivity.this).getGithubService().publicRepositories(charSequence.toString());
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        githubUser -> {
//                            System.out.println("=== updateUI");
//                            updateUI();
//                        },
//                        throwable -> {
//                            throwable.printStackTrace();
//                            Toast.makeText(SearchActivity.this, "搜索失败，请重试", Toast.LENGTH_SHORT).show();
//
//                        }
//                );
//        .filter(new Func1<CharSequence, Boolean>() {
//            @Override
//            public Boolean call(CharSequence charSequence) {
//
//                return charSequence.length() > 0;
//            }
//        })
//                .switchMap(new Func1<CharSequence, Observable<GithubUser>>() {
//                    @Override
//                    public Observable<GithubUser> call(CharSequence charSequence) {
//                        return App.getApplication(SearchActivity.this).getGithubService().userFromUrl(charSequence.toString());
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .subscribe(
//                        new Action1<GithubUser>() {
//                            @Override
//                            public void call(GithubUser githubUser) {
//                                updateUI();
//                            }
//                        }
//                );

    }

    private void updateUI() {

    }
}
