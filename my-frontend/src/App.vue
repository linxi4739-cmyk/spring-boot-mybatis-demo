<script setup>
import { ref } from 'vue'

// --- 响应式数据 ---
const userId = ref('')      // 查询输入框
const userData = ref(null)  // 单个查询结果
const newName = ref('')     // 新增姓名输入框
const userList = ref([])    // 用户列表数组
const isSubmitting = ref(false) // 防止重复点击的状态锁

// --- 1. 查询所有用户 ---
const fetchAll = async () => {
  try {
    const response = await fetch('http://localhost:8080/test/all')
    userList.value = await response.json()
  } catch (e) {
    console.error("加载列表失败", e)
  }
}

// --- 2. 按ID查询单个用户 ---
const searchUser = async () => {
  if (!userId.value) return alert("请输入内容")

  // 判断输入的是不是数字
  const isNumber = !isNaN(userId.value)

  // 根据判断结果，拼接不同的 URL
  const url = isNumber
      ? `http://localhost:8080/test/hello?id=${userId.value}`      // 如果是数字，走 ID 接口
      : `http://localhost:8080/test/findByName?name=${userId.value}` // 如果是文字，走名字接口

  try {
    const response = await fetch(url)
    userData.value = await response.json()
  } catch (e) {
    alert("未找到该用户")
    userData.value = null
  }
}

// --- 3. 新增用户 (带防抖逻辑) ---
const addUser = async () => {
  if (isSubmitting.value) return
  if (!newName.value) return alert("请输入名字")

  try {
    isSubmitting.value = true
    const response = await fetch(`http://localhost:8080/test/add?name=${newName.value}`)
    const message = await response.text()
    alert(message)
    newName.value = '' // 清空输入框
    fetchAll() // 自动刷新列表
  } catch (e) {
    alert("添加失败")
  } finally {
    isSubmitting.value = false
  }
}

// --- 4. 删除用户 (新补全) ---
const removeUser = async (id) => {
  if (!confirm(`确定要删除 ID 为 ${id} 的用户吗？`)) return
  try {
    //const response = await fetch(`http://localhost:8080/test/delete?id=${id}`)
    const response = await fetch(`http://localhost:8080/test/findByName?name=${userId.value}`)
    const msg = await response.text()
    alert(msg)
    fetchAll() // 删完刷新列表
  } catch (e) {
    alert("删除出错")
  }
}

// 页面初始化加载列表
fetchAll()
</script>

<template>
  <div style="max-width: 600px; margin: 40px auto; font-family: sans-serif; padding: 20px;">
    <h1 style="text-align: center; color: #2c3e50;">用户管理系统 v1.0</h1>

    <div style="background: #e8f5e9; padding: 20px; border-radius: 8px; margin-bottom: 20px;">
      <h3 style="margin-top:0">✨ 新增用户</h3>
      <input v-model="newName" placeholder="输入新用户姓名" style="padding: 8px; width: 60%;"/>
      <button
          @click="addUser"
          :disabled="isSubmitting"
          :style="{
          padding: '8px 15px',
          background: isSubmitting ? '#cccccc' : '#4caf50',
          color: 'white', border: 'none', borderRadius: '4px',
          cursor: isSubmitting ? 'not-allowed' : 'pointer', marginLeft: '5px'
        }"
      >
        {{ isSubmitting ? '保存中...' : '提交保存' }}
      </button>
    </div>

    <div style="background: #f1f8ff; padding: 20px; border-radius: 8px; margin-bottom: 20px;">
      <h3 style="margin-top:0">🔍 查询用户</h3>
      <input
          v-model="userId"
          placeholder="请输入 ID 或姓名"  style="padding: 8px; width: 60%;"
          @keyup.enter="searchUser"
      />
      <button @click="searchUser" style="padding: 8px 15px; background: #2196f3; color: white; border: none; border-radius: 4px; cursor: pointer; margin-left: 5px;">
        立即查询
      </button>
      <div v-if="userData" style="margin-top: 15px; background: white; padding: 10px; border-radius: 4px;">
        <p style="margin: 5px 0;"><b>ID:</b> {{ userData.id }} | <b>姓名:</b> {{ userData.name }}</p>
      </div>
    </div>

    <div style="margin-top: 30px; border-top: 2px solid #42b983; padding-top: 20px;">
      <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
        <h3 style="margin:0">👥 所有用户列表</h3>
        <button @click="fetchAll" style="padding: 5px 10px;">刷新列表</button>
      </div>

      <table border="1" style="width: 100%; border-collapse: collapse; text-align: center; background: white;">
        <thead>
        <tr style="background: #f4f4f4;">
          <th style="padding: 10px;">ID</th>
          <th>姓名</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="user in userList" :key="user.id">
          <td style="padding: 8px;">{{ user.id }}</td>
          <td>{{ user.name }}</td>
          <td>
            <button @click="removeUser(user.id)" style="background: #ff4d4f; color: white; border: none; padding: 4px 8px; border-radius: 4px; cursor: pointer;">
              删除
            </button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>