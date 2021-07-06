import {get,post} from "./http";

//============活动相关================

//查询活动
export const getAllActivity =() => get(`activity/allActivity`);

//添加活动
export const setActivity = (params) => post(`activity/add`,params);

//编辑活动
export const updateActivity = (params) => post(`activity/update`,params);
//删除活动
export const delActivity = (id) => get(`activity/delete?id=${id}`);

//============章节相关================

//查询章节
export const getAllChapter =() => get(`chapter/allChapter`);

//根据活动id查询歌曲
export const chapterOfActivityID =(id) => get(`chapter/activity/detail?activityID=${id}`);

//添加章节
export const setChapter = (params) => post(`chapter/add`,params);

//编辑章节
export const updateChapter = (params) => post(`chapter/update`,params);
//删除章节
export const delChapter = (id) => get(`chapter/delete?id=${id}`);

//============内容相关================

//查询章节
export const getAllContent =() => get(`content/allContent`);

//根据活动id和章节id查询歌曲
export const contentOfActivityIDAndChapterID = (activityID, chapterID) => get(`content/chapter/activity/detail?activityID=${activityID}&chapterID=${chapterID}`);

//添加内容
export const setContent = (params) => post(`content/add`,params);

//编辑内容
export const updateContent = (params) => post(`content/update`,params);
//删除内容
export const delContent = (id) => get(`content/delete?id=${id}`);

//============题目相关================

//查询题目
export const getAllProblem =() => get(`problem/allProblem`);

//根据活动id和章节id和内容id查询歌曲
export const problemOfActivityIDAndChapterIDAndContentID = (activityID, chapterID, contentID) => get(`problem/content/chapter/activity/detail?activityID=${activityID}&chapterID=${chapterID}&contentID=${contentID}`);

//添加题目
export const setProblem = (params) => post(`problem/add`,params);

//编辑题目
export const updateProblem = (params) => post(`problem/update`,params);
//删除题目
export const delProblem = (id) => get(`problem/delete?id=${id}`);

/**
//============歌曲相关================
//根据歌手id查询歌曲
export const songOfSingerId =(id) => get(`song/singer/detail?singerId=${id}`);
//编辑歌曲
export const updateSong = (params) => post(`song/update`,params);
//删除歌曲
export const delSong = (id) => get(`song/delete?id=${id}`);
//根据歌曲id查询歌曲对象
export const songOfSongId =(id) => get(`song/detail?songId=${id}`);
//根据歌曲名获取歌曲对象
export const songOfSongName =(songName) => get(`song/songOfSongName?songName=${songName}`);
//查询所有歌曲
export const allSong =() => get(`song/allSong`);

//============歌单相关================
//查询歌单
export const getAllSongList =() => get(`songList/allSongList`);
//添加歌单
export const setSongList = (params) => post(`songList/add`,params);
//编辑歌单
export const updateSongList = (params) => post(`songList/update`,params);
//删除歌单
export const delSongList = (id) => get(`songList/delete?id=${id}`);

//============歌单的歌曲相关============
//根据歌单id查询歌曲列表
export const listSongDetail = (songListId) => get(`listSong/detail?songListId=${songListId}`);
//给歌单增加歌曲
export const listSongAdd = (params) => post(`listSong/add`,params);
//删除歌单的歌曲
export const delListSong = (songId,songListId) => get(`listSong/delete?songId=${songId}&songListId=${songListId}`);

//============用户相关================
//查询用户
export const getAllConsumer =() => get(`consumer/allConsumer`);
//添加用户
export const setConsumer = (params) => post(`consumer/add`,params);
//编辑用户
export const updateConsumer = (params) => post(`consumer/update`,params);
//删除用户
export const delConsumer = (id) => get(`consumer/delete?id=${id}`);
//根据用户id查询该用户的详细信息
export const getUserOfId =(id) => get(`/consumer/selectByPrimaryKey?id=${id}`);

//===============收藏===================
//指定用户的收藏列表
export const getCollectOfUserId = (userId) => get(`/collect/collectOfUserId?userId=${userId}`);
//删除用户收藏的歌曲
export const deleteCollection = (userId,songId) => get(`collect/delete?userId=${userId}&songId=${songId}`);

//===============评论===================
//指定歌单的评论列表
export const getCommentOfSongListId = (songListId) => get(`/comment/commentOfSongListId?songListId=${songListId}`);
//删除评论
export const deleteComment = (id) => get(`comment/delete?id=${id}`);

*/