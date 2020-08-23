
predicate red_blue_only(arr: array?<char>)
  requires arr != null
  reads arr
{
  forall i :: 0 <= i < arr.Length ==> arr[i] == 'r' || arr[i] == 'b'
}

predicate red_only(arr: array?<char>)
  requires arr != null
  reads arr
{
  forall i :: 0 <= i < arr.Length ==> arr[i] == 'r'
}

predicate blue_only(arr: array?<char>)
  requires arr != null
  reads arr
{
  forall i :: 0 <= i < arr.Length ==> arr[i] == 'b'
}

method swap(arr: array?<char>, a: int, b: int)
  requires arr != null && 0 <= a < b < arr.Length
  requires red_blue_only(arr)
  ensures old(arr[b]) == arr[a] && old(arr[a]) == arr[b]
  ensures forall k :: 0 <= k < arr.Length && k != a && k != b ==> old(arr[k]) == arr[k]
  ensures red_blue_only(arr)
  modifies arr
{
  var temp := arr[b];
  arr[b] := arr[a];
  arr[a] := temp;
}

predicate red_half(arr: array?<char>, k: int)
  requires arr != null
  requires red_blue_only(arr)
  requires 0 <= k <= arr.Length
  reads arr
{
  (blue_only(arr) && k == 0) || forall i :: 0 <= i < k ==> arr[i] == 'r'
}

predicate blue_half(arr: array?<char>, k: int)
  requires arr != null
  requires red_blue_only(arr)
  requires 0 <= k <= arr.Length
  reads arr
{
  (red_only(arr) && k == arr.Length) || forall i :: k <= i < arr.Length ==> arr[i] == 'b'
}

method dutch(arr: array?<char>) returns (k: int)
  requires arr != null && arr.Length != 0
  requires red_blue_only(arr)
  ensures 0 <= k <= arr.Length
  ensures red_blue_only(arr)
  ensures red_half(arr, k)
  ensures blue_half(arr, k)
  modifies arr
{
  var j := 0;
  k := arr.Length;
  while (j < k)
    invariant 0 <= j <= k <= arr.Length
    invariant red_blue_only(arr)
    invariant red_half(arr, j)
    invariant blue_half(arr, k)
    decreases k - j
  {
    if (arr[j] == 'b' && arr[k-1] == 'r') {
      swap(arr, j, k-1);
    }

    if (arr[j] == 'r') {
      j := j + 1;
    } else if (arr[k-1] == 'b') {
      k := k - 1;
    }
  }
}
